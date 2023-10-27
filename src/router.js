import Vue from "vue"
import VueRouter from "vue-router"

import Home from "./views/Home"
import Login from "./views/Login"
import Register from "./views/Register"
import Item from "./views/Item"
import Tool from "./views/Tool"

Vue.use(VueRouter)

export default new VueRouter({
    mode: 'history',
    routes: [
        {
            path: "/",
            name: "home",
            component: Home
        },
        {
            path: "/login",
            name: "login",
            component: Login
        },
        {
            path: "/register",
            name: "register",
            component: Register
        },
        {
            path: "/tool",
            name: "tool",
            component: Tool
        },
        {
            path: "/item",
            name: "item",
            component: Item,
            meta: {
                auth: true,
            }
        },
    ]
})