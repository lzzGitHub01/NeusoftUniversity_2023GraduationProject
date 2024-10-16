import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  { // 重定向，用于实现刷新操作
    path: '/redirect/:path(.*)',
    component: () => import('../views/redirectComponent/redirect.vue')
  },
  
  {
    path: '/',
    name: 'UserLogin',
    component: () => import('../views/Login.vue')
  },
  
  {
    path: '/homePage_LH',
    name: 'HomePage_LH',
    component: () => import('../views/homePage/homePage_LEAVE_HIGH.vue'),
    children:[
      {
        path: '',
        component: () => import('../views/functionPage_LH/area_LH/DefaultHomePage.vue')
      },
      {
        path: '/AreaManagement',
        component: () => import('../views/functionPage_LH/area_LH/AreaManageMent.vue')
      },
      {
        path: '/BuildManageMent',
        component: () => import('../views/functionPage_LH/area_LH/BuildManageMent.vue')
      },
      {
        path: '/PersonalManageMent',
        component: () => import('../views/functionPage_LH/area_LH/PersonalManageMent.vue')
      },
      {
        path: '/AccountManagement',
        component: () => import('../views/functionPage_LH/userManager_LH/AccountManagement.vue')
      },
      {
        path: '/CurrentAccount',
        component: () => import('../views/functionPage_LH/userManager_LH/CurrentAccount.vue')
      },
      {
        path: '/InfectionStatus',
        component: () => import('../views/functionPage_LH/InfectionStatus_LH/InfectionStatus.vue')
      },
      {
        path: '/BlockManageMent',
        component: () => import('../views/functionPage_LH/blockManageMent_LH/BlockManageMent.vue')
      },
      
    ]
  },
  {
    path: '/homePage_LM',
    name: 'HomePage_LM',
    component: () => import('../views/homePage/homePage_LEAVE_MEDIUM.vue'),
    children:[
      {
        path: '',
        component: () => import('../views/functionPage_LH/area_LH/DefaultHomePage.vue')
      },
      {
        path: '/InfectionStatus_LM',
        component: () => import('../views/functionPage_LM/InfectionStatus_LM/InfectionStatus_LM.vue')
      },
      {
        path: '/BuildManageMent_LM',
        component: () => import('../views/functionPage_LM/area_LM/BuildManageMent_LM.vue')
      },
      {
        path: '/PersonalManageMent_LM',
        component: () => import('../views/functionPage_LM/personal_LM/PersonalManageMent_LM.vue')
      },
      {
        path: '/AccountManagement_LM',
        component: () => import('../views/functionPage_LM/userManager_LM/AccountManagement_LM.vue')
      },
      {
        path: '/CurrentAccount_LM',
        component: () => import('../views/functionPage_LM/userManager_LM/CurrentAccount_LM.vue')
      },
      // {
      //   path: '/LM-test01',
      //   component: () => import('#')
      // },
      // {
      //   path: '/LM-test02',
      //   component: () => import('#')
      // },
      // {
      //   path: '/LM-test03',
      //   component: () => import('#')
      // },
    ]
  },
  {
    path: '/homePage_LN',
    name: 'HomePage_LN',
    component: () => import('../views/homePage/homePage_LEAVE_NORMAL.vue'),
    children:[
      {
        path: '',
        component: () => import('../views/functionPage_LH/area_LH/DefaultHomePage.vue')
      },
      {
        path: '/BuildManageMent_LN',
        component: () => import('../views/functionPage_LN/build_LN/BuildManageMent_LN.vue')
      },
      {
        path: '/InfectionStatus_LN',
        component: () => import('../views/functionPage_LN/InfectionStatus_LN/InfectionStatus_LN.vue')
      },
      {
        path: '/PersonalManageMent_LN',
        component: () => import('../views/functionPage_LN/personal_LN/PersonalManageMent_LN.vue')
      },
      {
        path: '/CurrentAccount_LN',
        component: () => import('../views/functionPage_LN/userManager_LN/CurrentAccount_LN.vue')
      },
      
      // {
      //   path: '/LN-test01',
      //   component: () => import('#')
      // },
      // {
      //   path: '/LN-test02',
      //   component: () => import('#')
      // },
      // {
      //   path: '/LN-test03',
      //   component: () => import('#')
      // },
    ]
  },

  {
    path: '/homePage_SYS',
    name: 'HomePage_SYS',
    component: () => import('../views/homePage/homePage_SYSTEM.vue'),
    children:[
      {
        path: '',
        component: () => import('../views/functionPage_LH/area_LH/DefaultHomePage.vue')
      },
      {
        path: '/CommunityManageMent',
        component: () => import('../views/functionPage_SYS/CommunityManageMent.vue')
      },
      {
        path: '/CurrentAccount_SYS',
        component: () => import('../views/functionPage_SYS/CurrentAccount.vue')
      },
      // {
      //   path: '/InfectionStatus_LN',
      //   component: () => import('../views/functionPage_LN/InfectionStatus_LN/InfectionStatus_LN.vue')
      // },
      // {
      //   path: '/PersonalManageMent_LN',
      //   component: () => import('../views/functionPage_LN/personal_LN/PersonalManageMent_LN.vue')
      // },
      // {
      //   path: '/CurrentAccount_LN',
      //   component: () => import('../views/functionPage_LN/userManager_LN/CurrentAccount_LN.vue')
      // },
      
      // {
      //   path: '/LN-test01',
      //   component: () => import('#')
      // },
      // {
      //   path: '/LN-test02',
      //   component: () => import('#')
      // },
      // {
      //   path: '/LN-test03',
      //   component: () => import('#')
      // },
    ]
  },
]

const router = createRouter({
  // @ts-ignore
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
