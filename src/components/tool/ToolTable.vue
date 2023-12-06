<template>
    <table class="table table-striped" ref="toolTable" style="width:100%">
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Current Quantity</th>
                <th>Total Quantity</th>
                <th>Image</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <template v-if="tools">
            <tr v-for="tool in tools" :key="tool.id">
                <td>{{ tool.id }}</td>
                <td>{{ tool.name }}</td>
                <td>{{ tool.current_quantity}}</td>
                <td>{{ tool.total_quantity}}</td>
                <td><img :src="'http://localhost:8090/api/tool/getImage/' + tool.filename" :alt="tool.name" width="100rem"></td>
                <td>
                    <button class="btn btn-warning me-2"><i class="bi bi-pencil-square"></i></button>
                    <button class="btn btn-danger" @click="deleteTool(tool.id)"><i class="bi bi-trash"></i></button>
                </td>
            </tr>
            </template>
        </tbody>
    </table>    
</template>

<script>
import $ from "jquery"
import ToolService from "../../services/tool.service"

export default {
    name: "ToolTable",
    data() {
        return {
            tools: [],
            dataTable: $('#toolTable')
        }
    },
    methods: {
        deleteTool(id) {
            ToolService.delete(id).then( () => {
                this.tools = this.tools.filter(tool => tool.id !== id)
                
                $(this.$refs.toolTable).DataTable().destroy()

                this.$nextTick(() => {
                    $(this.$refs.toolTable).DataTable()
                });
            })
        },
        getAllTools() {
            ToolService.getAll().then( response => {
                this.tools = response.data.data
                this.$nextTick(() => {
                    $(this.$refs.toolTable).DataTable()
            })
        })
        }
    },
    mounted() {
        this.getAllTools();
    },
}
</script>

<style>
@import 'datatables.net-bs5';
</style>