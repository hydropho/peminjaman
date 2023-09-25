import AuthService from "@/services/auth.service";

export default {
    namespaced: true,
    state: {
        loggedIn: false,
        user: null,
    },
    mutations: {
        loginSuccess(state, user) {
            state.loggedIn = true;
            state.user = user;
        },
        loginFailure(state) {
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
        }
    },
}