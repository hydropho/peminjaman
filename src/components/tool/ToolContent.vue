<template>
  <div class="container-fluid">
    <h1>List Tools</h1>
    <ToolTable/>
  </div>
</template>

<script>
import ToolTable from './ToolTable.vue'

export default {
    name: "ToolContent",
    components: {
        ToolTable,
    }
}
</script>

<style>

</style>