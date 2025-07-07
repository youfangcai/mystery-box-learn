import {createRouter, createWebHashHistory, createWebHistory} from 'vue-router'

import {useHomeStore} from '@/stores/home-store'

const router = createRouter({
    history: createWebHashHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'layout-view',
            component: () => import('@/layout/layout-view.vue'),
            children: [
                {path: '/user', component: () => import('@/views/user/user-view.vue')},
                {
                    path: '/role',
                    name: 'role',
                    component: () => import('@/views/role/role-view.vue')
                },
                {
                    path: '/menu',
                    name: 'menu',
                    component: () => import('@/views/menu/menu-view.vue')
                },
                {
                    path: '/dict',
                    name: 'dict',
                    component: () => import('@/views/dict/dict-view.vue')
                },
                {
                    path: '/user',
                    component: () => import('@/views/user/user-view.vue')
                },
                {
                    path: '/user-details',
                    component: () => import('@/views/user/user-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/vip',
                    component: () => import('@/views/vip/vip-view.vue')
                },
                {
                    path: '/vip-details',
                    component: () => import('@/views/vip/vip-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/coupon-category-rel',
                    component: () => import('@/views/coupon-category-rel/coupon-category-rel-view.vue')
                },
                {
                    path: '/coupon-category-rel-details',
                    component: () => import('@/views/coupon-category-rel/coupon-category-rel-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/refund-record',
                    component: () => import('@/views/refund-record/refund-record-view.vue')
                },
                {
                    path: '/refund-record-details',
                    component: () => import('@/views/refund-record/refund-record-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/vip-order',
                    component: () => import('@/views/vip-order/vip-order-view.vue')
                },
                {
                    path: '/vip-order-details',
                    component: () => import('@/views/vip-order/vip-order-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/dict',
                    component: () => import('@/views/dict/dict-view.vue')
                },
                {
                    path: '/dict-details',
                    component: () => import('@/views/dict/dict-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/slideshow',
                    component: () => import('@/views/slideshow/slideshow-view.vue')
                },
                {
                    path: '/slideshow-details',
                    component: () => import('@/views/slideshow/slideshow-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/menu',
                    component: () => import('@/views/menu/menu-view.vue')
                },
                {
                    path: '/menu-details',
                    component: () => import('@/views/menu/menu-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/mystery-box-category',
                    component: () => import('@/views/mystery-box-category/mystery-box-category-view.vue')
                },
                {
                    path: '/mystery-box-category-details',
                    component: () => import('@/views/mystery-box-category/mystery-box-category-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/vip-package',
                    component: () => import('@/views/vip-package/vip-package-view.vue')
                },
                {
                    path: '/vip-package-details',
                    component: () => import('@/views/vip-package/vip-package-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/user-we-chat',
                    component: () => import('@/views/user-we-chat/user-we-chat-view.vue')
                },
                {
                    path: '/user-we-chat-details',
                    component: () => import('@/views/user-we-chat/user-we-chat-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/payment',
                    component: () => import('@/views/payment/payment-view.vue')
                },
                {
                    path: '/payment-details',
                    component: () => import('@/views/payment/payment-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/address',
                    component: () => import('@/views/address/address-view.vue')
                },
                {
                    path: '/address-details',
                    component: () => import('@/views/address/address-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/mystery-box-product-rel',
                    component: () => import('@/views/mystery-box-product-rel/mystery-box-product-rel-view.vue')
                },
                {
                    path: '/mystery-box-product-rel-details',
                    component: () => import('@/views/mystery-box-product-rel/mystery-box-product-rel-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/feedback',
                    component: () => import('@/views/feedback/feedback-view.vue')
                },
                {
                    path: '/feedback-details',
                    component: () => import('@/views/feedback/feedback-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/product',
                    component: () => import('@/views/product/product-view.vue')
                },
                {
                    path: '/product-details',
                    component: () => import('@/views/product/product-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/mystery-box',
                    component: () => import('@/views/mystery-box/mystery-box-view.vue')
                },
                {
                    path: '/mystery-box-details',
                    component: () => import('@/views/mystery-box/mystery-box-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/coupon-receive-activity',
                    component: () => import('@/views/coupon-receive-activity/coupon-receive-activity-view.vue')
                },
                {
                    path: '/coupon-receive-activity-details',
                    component: () => import('@/views/coupon-receive-activity/coupon-receive-activity-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/coupon',
                    component: () => import('@/views/coupon/coupon-view.vue')
                },
                {
                    path: '/coupon-details',
                    component: () => import('@/views/coupon/coupon-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/product-category',
                    component: () => import('@/views/product-category/product-category-view.vue')
                },
                {
                    path: '/product-category-details',
                    component: () => import('@/views/product-category/product-category-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/carriage-template',
                    component: () => import('@/views/carriage-template/carriage-template-view.vue')
                },
                {
                    path: '/carriage-template-details',
                    component: () => import('@/views/carriage-template/carriage-template-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/coupon-user-rel',
                    component: () => import('@/views/coupon-user-rel/coupon-user-rel-view.vue')
                },
                {
                    path: '/coupon-user-rel-details',
                    component: () => import('@/views/coupon-user-rel/coupon-user-rel-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
                {
                    path: '/coupon-box-rel',
                    component: () => import('@/views/coupon-box-rel/coupon-box-rel-view.vue')
                },
                {
                    path: '/coupon-box-rel-details',
                    component: () => import('@/views/coupon-box-rel/coupon-box-rel-details-view.vue'),
                    props(to) {
                        return {id: to.query.id}
                    }
                },
            ]
        },
        {
            path: '/login',
            name: 'login',
            component: () => import('@/views/login/login-view.vue')
        },
        {
            path: '/register',
            name: 'register',
            component: () => import('@/views/login/register-view.vue')
        },
        {
            path: '/rest-password',
            component: () => import('@/views/login/rest-password-view.vue')
        }
    ]
})
// 添加路由拦截，在进入路由之前需要校验是否有该菜单的权限
// eslint-disable-next-line no-sparse-arrays
const whiteList = ['/login', '/register', '/rest-password', '/']
router.beforeEach(async (to, from, next) => {
    const homeStore = useHomeStore()
    if (
        whiteList.includes(to.path) ||
        (await homeStore.getMenuList()).findIndex((menu) => menu.path === to.path) >= 0
    ) {
        next()
    } else {
        return next('/')
    }
})
export default router
