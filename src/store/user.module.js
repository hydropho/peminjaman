import AuthService from "@/services/auth.service";

const user = JSON.parse(localStorage.getItem('user'));
const initialState = user
    ? { loggedIn: true, user }
    : { loggedIn: false, user: null };

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
                    user => {
                        commit('loginSuccess', user);
                        return Promise.resolve(user);
                    },
                    error => {
                        commit('loginFailure');
                        return Promise.reject(error);
                    }
                )
        },
        logout({ commit }) {
            AuthService.logout();
            commit('logout');
        }
    },
}