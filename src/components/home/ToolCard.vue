<script lang="ts">
export default {
  name: "ToolCard",
  data() {
    return {
      toolName: "Drill",
    };
  },
};
</script>

<template>
  <div class="card-container p-2 rounded-lg shadow">
    <h1 class="mb-1">Drill</h1>
    <b-img
      src="https://picsum.photos/600/300/?image=25"
      fluid
      rounded="lg"
      :alt="toolName"
    />
    <div class="d-flex justify-content-between mt-2 mb-1 align-items-center">
      <h4>Stock: 5</h4>
      <b-button pill variant="primary" @click="$emit('showModal', toolName)">
        Borrow
      </b-button>
    </div>
  </div>
</template>

<style scoped>
.card-container {
  background-color: white;
}

.bi-plus-cicrle-fill {
  width: 28px;
  height: 28px;
}
</style>
