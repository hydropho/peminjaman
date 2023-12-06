import AuthService from "@/services/auth.service";

let user = JSON.parse(localStorage.getItem('user'));
let initialState = user ?
    (new Date().getTime() > user.expire ?
        { loggedIn: false, user: null } :
        { loggedIn: true, user }
    ) :
    { loggedIn: false, user: null };

export default {
    namespaced: true,
    state: initialState,
    mutations: {
        loginSuccess(state, user) {
            state.loggedIn = true;
            state.user = user;
        },
        loginFailure(state) {
            state.loggedIn = false;
            state.user = null;
        },
        logout(state) {
            state.loggedIn = false;
            state.user = null;
        }
    },
    actions: {
        login({ commit }, user) {
            return AuthService.login(user)
                .then(
                    response => {
                        if (response.success) {
                            commit('loginSuccess', response.data);
                            return Promise.resolve(response.data);
                        }
                        commit('loginFailure');
                        return Promise.reject(response);
                    },
                )
        },
        logout({ commit }) {
            AuthService.logout();
            commit('logout');
        }
    },
    getters: {
        getFirstName(state) {
            return state.user ? state.user.name.split(" ")[0] : null;
        }
    }
}