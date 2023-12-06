<template>
  <div>
    <Navbar/>
    <ToolContent/>
  </div>
</template>

<script>
import Navbar from '@/components/Navbar.vue'
import ToolContent from '@/components/tool/ToolContent.vue'

export default {
    name: "ToolPage",
    components: {
        Navbar,
        ToolContent,
    }
}
</script>

<style>

</style>