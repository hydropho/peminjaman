<template>
  <div>
    <div class="header">
      <h1>Alat</h1>
    </div>

    <div class="home-content"></div>
  </div>
</template>

<script>
export default {
  name: "ItemPage",
  components: {},
};
</script>

<style lang="scss" scoped>
.header {
  margin-bottom: 2rem;
}
.home-content {
  display: grid;
  grid-template-rows: 1fr 4fr;
  row-gap: 2rem;
}
</style>