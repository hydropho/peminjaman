<template>
  <div class="modal modal-lg fade" id="exampleModal" ref="addToolModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Add Tool</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form @submit.prevent="addTool">
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="tool-name" class="form-label">Tool name</label>
                        <input type="text" class="form-control" id="tool-name" v-model="tool.name" v-validate="'required'" name="tool-name">
                        <p v-if="errors.has('tool-name')" class="text-danger">Name cannot be empty.</p>
                    </div>
                    <div class="mb-3">
                        <label for="tool-current-quantity" class="form-label">Current quantity</label>
                        <input type="number" class="form-control" id="tool-current-quantity" v-model="tool.currentQuantity" v-validate="'required'" name="tool-current-quantity">
                        <p v-if="errors.has('tool-current-quantity')" class="text-danger">Current quantity cannot be empty.</p>
                    </div>
                    <div class="mb-3">
                        <label for="tool-total-quantity" class="form-label">Total quantity</label>
                        <input type="number" class="form-control" id="tool-total-quantity" v-model="tool.totalQuantity" v-validate="'required'" name="tool-total-quantity">
                        <p v-if="errors.has('tool-total-quantity')" class="text-danger">Total quantity cannot be empty.</p>
                    </div>
                    <div class="mb-3">
                        <label for="tool-image" class="form-label">Total quantity</label>
                        <input type="file" class="form-control" id="tool-image" accept=".jpg, .jpeg, .png" @change="handleInputFile" v-validate="'required|image|ext:jpg,jpeg,png'" name="tool-image">
                        <p v-if="errors.has('tool-image')" class="text-danger">{{ errorImageMessage }}</p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary"><span v-if="loading" class="spinner-border spinner-border-sm"></span><span v-else>Submit</span></button>
                </div>              
            </form>
        </div>
      </div>
    </div>
</template>

<script>
import ToolService from '@/services/tool.service'
import { Modal } from 'bootstrap'

export default {
    name: "ToolAddModal",
    data() {
        return {
            tool: {
                name: "",
                currentQuantity: null,
                totalQuantity: null,
            },
            image: null,
            loading: false,
        }
    },
    methods: {
        handleInputFile(event) {
            if (!this.errors.has('tool-image')) {
                const file = event.target.files[0]

                this.image = file
            }
        },
        addTool(){
            const formData = new FormData()

            this.$validator.validateAll().then((isValid) => {
                this.loading = true;
                if (isValid) {
                    const json = JSON.stringify(this.tool);
                    const blob = new Blob([json], {
                        type: 'application/json'
                    });
                    
                    formData.append('data', blob)
                    formData.append('image', this.image)

                    ToolService.add(formData).then(
                        () => {
                            let myModal = new Modal(this.$refs.addToolModal)
                            myModal.hide()
                        }
                    )
                }
                this.loading = false;
            });
        }
    },
    computed: {
        errorImageMessage() {
            switch (this.errors.first('tool-image')) {
                case "The tool-image field must be an image":
                    return "You can only upload image."
                case "The tool-image field is required":
                    return "Image cannot be empty."
                default:
                    return ''
            }
        }
    }
}
</script>

<style>

</style>