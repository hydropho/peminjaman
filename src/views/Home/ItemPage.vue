<script lang="ts">
import ItemTable from "@/components/item/ItemTable.vue";
export default {
  name: "ItemPage",
  components: {
    ItemTable,
  },
};
</script>

<template>
  <div>
    <div class="py-5 d-flex d-lg-block flex-column align-items-center">
      <h1>Item</h1>
      <p>Your borrowed tools.</p>
    </div>
    <ItemTable></ItemTable>
  </div>
</template>
