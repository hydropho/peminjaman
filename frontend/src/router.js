import Vue from "vue"
import VueRouter from "vue-router"

import Login from './views/Login'
import Register from './views/Register'
import Item from './views/Home'
import KelolaItem from './views/KelolaItem'
import User from './views/User'

Vue.use(VueRouter)

export const router = new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/login',
            name: 'login',
            component: Login
        },
        {
            path: '/register',
            name: 'register',
            component: Register
        },
        {
            path: '/',
            name: 'home',
            component: Item
        },
        {
            path: '/kelola-item',
            name: 'kelola-item',
            component: KelolaItem
        },
        {
            path: '/user',
            name: 'user',
            component: User
        },
    ]
})