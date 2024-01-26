import Vue from 'vue'
import App from './App.vue'
import Vuex from 'vuex'
import VeeValidate from 'vee-validate'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'
import router from './router'
import store from './store'
import 'jquery/dist/jquery'
import 'datatables.net-bs5/'

Vue.config.productionTip = false

Vue.use(Vuex)
Vue.use(VeeValidate)

router.beforeEach((to, from, next) => { // eslint-disable-next-line
  if (to.meta.auth) {
    const user = store.state.user.user;

    if (user) {
      const expirationTime = user.expire;

      if (expirationTime && new Date().getTime() > expirationTime) {
        localStorage.removeItem('user')
        store.dispatch('user/logout')
        next({ name: 'login'})
      }
      else next()
    }
    else next({ name: 'login' })
  }
  else next()
})

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
