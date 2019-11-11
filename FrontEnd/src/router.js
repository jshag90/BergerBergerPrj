import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    },
    {
      path: '/about2',
      component: () => import('./views/About2.vue')
    },
    {
      path: '/lectures/card',
      component: () => import('./views/lectures/card.vue')
    },
    {
      path: '/lectures/layout',
      component: () => import('./views/lectures/layout.vue')
    },
    {
      path: '/lectures/notes',
      component: () => import('./views/lectures/notes.vue')
    },
    {
      path: '/burgerKing',
      component: () => import('./views/brands/BurgerKing.vue')
    },
    {
      path: '/burgerKing/:category',
      name: 'burgerkingMenus',
      component: () => import('./views/brands/BurgerKingMenus.vue'),
      beforeRouteUpdate (to, from, next) {
        // this.category = to.params.category
        next()
      }
    },
    {
      path: '/macdonald',
      component: () => import('./views/brands/Macdonalds.vue')
    },
    {
      path: '/macdonald/:category',
      name: 'macdonaldMenus',
      component: () => import('./views/brands/MacdonaldsMenus.vue')
    },
    {
      path: '/kfc',
      component: () => import('./views/brands/Kfc.vue')
    },
    {
      path: '/kfc/:category',
      name: 'kfcMenus',
      component: () => import('./views/brands/KfcMenus.vue')
    },
    {
      path: '*',
      component: () => import('./views/e404.vue')
    }
  ]
})
