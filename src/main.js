import Vue from 'vue'
import App from './App.vue'
import Vuex from 'vuex'
import VeeValidate from 'vee-validate'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'
import router from './router'
import store from './store'

Vue.config.productionTip = false

Vue.use(Vuex)
Vue.use(VeeValidate)

router.beforeEach((to, from, next) => { // eslint-disable-next-line
  if (to.meta.auth && !store.state.user.user) next({ name: 'login' })
  else next()
})

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
