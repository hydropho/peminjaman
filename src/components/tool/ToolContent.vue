<template>
  <div class="container-fluid">
    <div class="d-flex justify-content-between my-3">
      <h1>List Tools</h1>
      <button class="btn btn-primary rounded-pill h-100" data-bs-target="#exampleModal" @click="showModal">Add <i class="bi bi-plus-lg"></i></button>
    </div>
    <!-- Modal -->
    <ToolAddModal ref="modal"/>
    <ToolTable />
  </div>
</template>

<script>
import ToolAddModal from './ToolAddModal.vue'
import ToolTable from './ToolTable.vue'
import { Modal } from 'bootstrap'

export default {
    name: "ToolContent",
    components: {
        ToolTable,
        ToolAddModal
    },
    methods: {
      showModal(){
        let modal = new Modal(this.$refs.modal)
        modal.show()
      }
    }
}
</script>

<style>

</style>