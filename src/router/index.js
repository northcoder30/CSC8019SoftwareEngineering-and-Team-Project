import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AboutView from '../views/AboutView.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import CategoryView from '../views/CategoryView.vue'
import WishlistView from '../views/WishlistView.vue'
import ProfileView from '../views/ProfileView.vue'
import SettingsView from '../views/SettingsView.vue'
import BookDetailView from '@/views/BookDetailView.vue'



const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  { path: '/register', component: Register },
  { path: '/home', component: HomeView },
  { path: '/about', component: AboutView },
  { path: '/', redirect: '/home' },
  { path: '/home', component: HomeView },
  { path: '/category', component: CategoryView },
  { path: '/wishlist', component: WishlistView },
  { path: '/profile', component: ProfileView },
  { path: '/settings', component: SettingsView },
  { path: '/profile', component: ProfileView},
  {
    path: '/book/:id',
    name: 'BookDetail',
    component: () => import('@/views/BookDetailView.vue')
  },
  {
    path: '/buy/:id',
    name: 'PaymentPage',
    component: () => import('@/views/PaymentPage.vue')
  }

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router

