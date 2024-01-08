<script lang="ts">
import ToolCard from "@/components/home/ToolCard.vue";
import ToolModal from "@/components/home/ToolModal.vue";
export default {
  name: "HomePage",
  components: {
    ToolCard,
    ToolModal,
  },
  data() {
    return {
      modalTitle: "",
      toolName: "",
      dateNow: "",
      timeNow: "",
      minDate: null as unknown as Date,
      maxDate: null as unknown as Date,
    };
  },
  methods: {
    triggerShowModal(toolName: string) {
      this.toolName = toolName;
      this.$bvModal.show("my-modal");
      const now = new Date();

      const dateNow =
        now.getFullYear() + "-" + (now.getMonth() + 1) + "-" + now.getDate();

      const timeNow = now.toString().split(" ")[4];

      const minDate = new Date();

      const maxDate = now;
      maxDate.setMonth(maxDate.getMonth() + 1);

      this.dateNow = dateNow;
      this.timeNow = timeNow;
      this.minDate = minDate;
      this.maxDate = maxDate;
    },
  },
};
</script>

<template>
  <div>
    <div class="py-5 d-flex d-lg-block flex-column align-items-center">
      <h1>Home</h1>
      <p>Choose the tool you want to borrow.</p>
    </div>
    <b-row cols="1" cols-sm="2" cols-md="3" cols-lg="4">
      <b-col class="mb-4">
        <ToolCard @showModal="triggerShowModal"></ToolCard>
      </b-col>
      <b-col class="mb-4">
        <ToolCard @showModal="triggerShowModal"></ToolCard>
      </b-col>
      <b-col class="mb-4">
        <ToolCard @showModal="triggerShowModal"></ToolCard>
      </b-col>
      <b-col class="mb-4">
        <ToolCard @showModal="triggerShowModal"></ToolCard>
      </b-col>
    </b-row>
    <ToolModal
      :toolName="toolName"
      :dateNow="dateNow"
      :timeNow="timeNow"
      :minDate="minDate"
      :maxDate="maxDate"
    ></ToolModal>
  </div>
</template>
