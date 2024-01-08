<script lang="ts">
export default {
  name: "ItemTable",
  data() {
    return {
      items: [
        {
          name: "Drill",
          quantity: 2,
          borrowingDate: "24 Dec 2023 17:38:30",
          returningDate: "",
          borrowingStatus: "OPEN",
          borrowingApproval: "PENDING",
          returningApproval: "",
        },
        {
          name: "Hammer",
          quantity: 1,
          borrowingDate: "27 Dec 2023 17:38:00",
          returningDate: "",
          borrowingStatus: "BORROWED",
          borrowingApproval: "APPROVED",
          returningApproval: "",
        },
        {
          name: "Grill",
          quantity: 1,
          borrowingDate: "27 Dec 2023 18:38:00",
          returningDate: "28 Dec 2023 15:00:21",
          borrowingStatus: "RETURNED",
          borrowingApproval: "APPROVED",
          returningApproval: "APPROVED",
        },
      ],
      fields: [
        {
          key: "name",
          label: "Tool name",
          sortable: true,
        },
        {
          key: "quantity",
          label: "Quantity",
          sortable: true,
          class: "text-center",
        },
        {
          key: "borrowingStatus",
          label: "Borrowing status",
          class: "text-center",
        },
        {
          key: "borrowingDate",
          label: "Borrowing Date",
          sortable: true,
          class: "text-center",
        },
        {
          key: "returningDate",
          label: "Returning Date",
          sortable: true,
          class: "text-center",
        },
        {
          key: "borrowingApproval",
          label: "Borrowing approval",
          class: "text-center",
        },
        {
          key: "returningApproval",
          label: "Returning approval",
          class: "text-center",
        },
        {
          key: "action",
          label: "Action",
          class: "text-center",
        },
      ],
      filter: null,
      sortBy: "borrowingDate",
      sortDesc: false,
    };
  },
};
</script>

<template>
  <b-container fluid>
    <b-row align-h="end">
      <b-col cols="8" md="6">
        <b-form-group
          label="Search"
          label-for="filter-input"
          class="mb-3"
          label-cols="3"
          label-cols-md="2"
        >
          <b-input-group>
            <b-form-input
              id="filter-input"
              v-model="filter"
              type="search"
              placeholder="Type to Search"
            ></b-form-input>
          </b-input-group>
        </b-form-group>
      </b-col>
    </b-row>

    <!-- Main table element -->
    <b-table
      :items="items"
      :fields="fields"
      :filter="filter"
      :sort-by.sync="sortBy"
      :sort-desc.sync="sortDesc"
      hover
      striped
      responsive
    >
      <template #cell(borrowingStatus)="row">
        <b-badge v-if="row.value == 'OPEN'" pill variant="secondary">
          {{ row.value }}
        </b-badge>
        <b-badge v-else-if="row.value == 'RETURNED'" pill variant="success">
          {{ row.value }}
        </b-badge>
        <b-badge v-else-if="row.value == 'BORROWED'" pill variant="info">
          {{ row.value }}
        </b-badge>
      </template>

      <template #cell(returningDate)="row">
        <template v-if="row.value !== ''">{{ row.value }}</template>
        <template v-else>-</template>
      </template>

      <template #cell(borrowingApproval)="row">
        <b-badge v-if="row.value === 'PENDING'" pill variant="secondary">
          {{ row.value }}
        </b-badge>
        <b-badge v-else-if="row.value === 'APPROVED'" pill variant="success">
          {{ row.value }}
        </b-badge>
        <b-badge v-else-if="row.value === 'REJECTED'" pill variant="danger">
          {{ row.value }}
        </b-badge>
      </template>

      <template #cell(returningApproval)="row">
        <template v-if="row.value !== ''">
          <b-badge v-if="row.value === 'PENDING'" pill variant="secondary">
            {{ row.value }}
          </b-badge>
          <b-badge v-else-if="row.value === 'APPROVED'" pill variant="success">
            {{ row.value }}
          </b-badge>
          <b-badge v-else-if="row.value === 'REJECTED'" pill variant="danger">
            {{ row.value }}
          </b-badge>
        </template>
        <template v-else> - </template>
      </template>

      <template #cell(action)>
        <b-button pill variant="warning"> Return </b-button>
      </template>
    </b-table>
  </b-container>
</template>
