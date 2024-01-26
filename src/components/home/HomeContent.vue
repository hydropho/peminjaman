<template>
  <div class="container-fluid">
    <div class="row g-3 row-cols-2 row-cols-sm-3 row-cols-md-4 row-cols-lg-5 row-cols-xl-6 justify-content-center">
      <div class="col">
        <div class="shadow-sm rounded-3 d-flex flex-column">
          <img src="@/assets/wrench.png" alt="wrench" class="rounded-top" />
          <div class="content p-3 d-flex flex-column">
            <span class="fs-6">Wrench</span>
            <h1 class="fs-4 m-0">Stock : <span class="text-danger">2</span></h1>
            <button class="btn btn-danger rounded-pill mt-3">Pinjam</button>
          </div>
        </div>
      </div>
      <div class="col">
        <div class="shadow-sm rounded-3 d-flex flex-column">
          <img src="@/assets/grinding.jpg" alt="wrench" class="rounded-top" />
          <div class="content p-3 d-flex flex-column">
            <span class="fs-6">Grinding</span>
            <h1 class="fs-4 m-0">Stock : <span class="text-danger">3</span></h1>
            <button class="btn btn-danger rounded-pill mt-3">Pinjam</button>
          </div>
        </div>
      </div>
      <div class="col">
        <div class="shadow-sm rounded-3 d-flex flex-column">
          <img src="@/assets/drill.jpg" alt="wrench" class="rounded-top" />
          <div class="content p-3 d-flex flex-column">
            <span class="fs-6">Drill</span>
            <h1 class="fs-4 m-0">Stock : <span class="text-danger">5</span></h1>
            <button class="btn btn-danger rounded-pill mt-3">Pinjam</button>
          </div>
        </div>
      </div>
        <div class="shadow-sm rounded-3 d-flex flex-column">
          <img src="@/assets/welding.jpg" alt="wrench" class="rounded-top" />
          <div class="content p-3 d-flex flex-column">
            <span class="fs-6">Welding</span>
            <h1 class="fs-4 m-0">Stock : <span class="text-danger">1</span></h1>
            <button class="btn btn-danger rounded-pill mt-3">Pinjam</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HomeContent",
};
</script>

<style scoped>
img {
  object-fit: cover;
}

@media (max-width: 767.98px) {
  img {
    height: 10rem;
  }
}

@media (min-width: 768px) {
  img {
    height: 15rem;
  }
}
</style>