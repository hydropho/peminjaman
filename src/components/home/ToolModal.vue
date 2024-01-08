<script lang="ts">
export default {
  name: "ToolModal",
  props: {
    toolName: String,
    dateNow: String,
    timeNow: String,
  },
  data() {
    return {
      tool: {
        name: "" as string,
        quantity: 1,
        BorrowingDate: "" as string,
        BorrowingTime: "" as string,
      },
      maxVal: 2,
      loading: false,
    };
  },
  methods: {
    async borrow() {
      this.loading = true;

      const isValid = await this.$validator.validateAll();

      if (isValid) {
        console.log("ok");
      } else {
        console.log("not ok");
      }
      this.loading = false;
      // this.$bvModal.hide("my-modal");
    },

    validateState(ref: string): boolean | null {
      if (
        this.veeFields[ref] &&
        (this.veeFields[ref].dirty || this.veeFields[ref].validated)
      ) {
        return !this.veeErrors.has(ref);
      }
      return null;
    },
    clearData() {
      this.tool.quantity = 1;
    },
  },
  watch: {
    toolName() {
      this.tool.name = this.toolName as string;
    },
    dateNow() {
      this.tool.BorrowingDate = this.dateNow as string;
    },
    timeNow() {
      this.tool.BorrowingTime = this.timeNow as string;
    },
  },
};
</script>

<template>
  <b-modal
    ref="my-modal"
    id="my-modal"
    hide-footer
    title="Detail"
    centered
    no-close-on-esc
    no-close-on-backdrop
    @close="clearData"
  >
    <div>
      <b-form @submit.prevent="borrow">
        <b-form-group
          id="tool-name-input-group"
          label="Tool name:"
          label-for="tool-name-input"
        >
          <b-form-input
            id="tool-name-input"
            name="tool-name-input"
            type="text"
            disabled
            v-model="tool.name"
            v-validate="{ required: true }"
            data-vv-as="Name"
            :state="validateState('tool-name-input')"
          ></b-form-input>
          <b-form-invalid-feedback id="tool-name-input-invalid-feedback">{{
            veeErrors.first("tool-name-input")
          }}</b-form-invalid-feedback>
        </b-form-group>

        <b-form-group
          id="tool-quantity-input-group"
          label="Quantity:"
          label-for="tool-quantity-input"
          description="Change this field if you want to borrow more than 1 tool."
        >
          <b-form-input
            id="tool-quantity-input"
            name="tool-quantity-input"
            placeholder="Enter quantity"
            type="number"
            v-model="tool.quantity"
            v-validate="{ required: true, min_value: 1, max_value: maxVal }"
            data-vv-as="Quantity"
            :state="validateState('tool-quantity-input')"
          ></b-form-input>
          <b-form-invalid-feedback id="tool-quantity-input-invalid-feedback">{{
            veeErrors.first("tool-quantity-input")
          }}</b-form-invalid-feedback>
        </b-form-group>

        <b-form-group
          id="borrowing-date-input-group"
          label="Request at:"
          label-for="borrowing-date-input"
        >
          <b-input-group>
            <b-form-datepicker
              id="borrowing-date-input"
              name="borrowing-date-input"
              v-model="tool.BorrowingDate"
              disabled
              v-validate="{ required: true }"
              data-vv-as="Start date"
              :state="validateState('borrowing-date-input')"
            ></b-form-datepicker>
            <b-form-timepicker
              id="borrowing-time-input"
              name="borrowing-time-input"
              ref="borrowing-time-input"
              v-model="tool.BorrowingTime"
              disabled
              v-validate="{ required: true }"
              data-vv-as="Start time"
              :state="validateState('borrowing-time-input')"
            ></b-form-timepicker>
          </b-input-group>
        </b-form-group>

        <!-- <b-form-group
          id="end-borrowing-date-input-group"
          label="End of borrowing:"
          label-for="end-borrowing-date-input"
          description="If you want to use it within same day, please select +1 hour and +1 minute."
        >
          <b-input-group>
            <b-form-datepicker
              id="end-borrowing-date-input"
              name="end-borrowing-date-input"
              :min="cal.minDate"
              :max="cal.maxDate"
              today-button
              reset-button
              v-model="tool.endBorrowingDate"
              v-validate="{ required: true }"
              data-vv-as="End date"
              :state="validateState('end-borrowing-date-input')"
            ></b-form-datepicker>
            <b-form-timepicker
              id="end-borrowing-time-input"
              name="end-borrowing-time-input"
              now-button
              reset-button
              no-close-button
              v-model="tool.endBorrowingTime"
              v-validate="{
                required: true,
                timeAfter: [
                  tool.startBorrowingTime,
                  tool.startBorrowingDate,
                  tool.endBorrowingDate,
                ],
              }"
              data-vv-as="End time"
              :state="validateState('end-borrowing-time-input')"
            ></b-form-timepicker>
          </b-input-group>
          <b-form-invalid-feedback
            id="time-borrowing-date-input-invalid-feedback"
            >{{
              veeErrors.first("end-borrowing-time-input")
            }}</b-form-invalid-feedback
          >
        </b-form-group> -->

        <div class="d-flex justify-content-end">
          <b-button type="submit" variant="primary">
            <b-spinner label="Spinning" v-if="loading"></b-spinner>
            <span v-else>Submit</span>
          </b-button>
        </div>
      </b-form>
    </div>
  </b-modal>
</template>

<style>
#borrowing-time-input__value_ {
  display: flex;
  align-items: center;
}

#end-borrowing-time-input__value_ {
  display: flex;
  align-items: center;
}
</style>

<style scoped>
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
</style>
