
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ReservationReservationManager from "./components/listers/ReservationReservationCards"
import ReservationReservationDetail from "./components/listers/ReservationReservationDetail"

import YogaclassYogaClassManager from "./components/listers/YogaclassYogaClassCards"
import YogaclassYogaClassDetail from "./components/listers/YogaclassYogaClassDetail"




export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/reservations/reservations',
                name: 'ReservationReservationManager',
                component: ReservationReservationManager
            },
            {
                path: '/reservations/reservations/:id',
                name: 'ReservationReservationDetail',
                component: ReservationReservationDetail
            },

            {
                path: '/yogaclasses/yogaClasses',
                name: 'YogaclassYogaClassManager',
                component: YogaclassYogaClassManager
            },
            {
                path: '/yogaclasses/yogaClasses/:id',
                name: 'YogaclassYogaClassDetail',
                component: YogaclassYogaClassDetail
            },





    ]
})
