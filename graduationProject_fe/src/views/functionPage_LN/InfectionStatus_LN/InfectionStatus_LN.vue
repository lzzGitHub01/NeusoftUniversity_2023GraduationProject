<template>
    <div class="pageStyle">
        <el-row :gutter="10" style="margin-bottom:10px">
            <el-col :span="16">
                <el-card shadow="hover">
                    <div class="demo-progress displaStyle">

                        <div>
                            <!-- 总人口环形图 -->
                            <el-progress type="circle" :percentage="infectionData.totalNum">
                                <template #default="{ percentage }">
                                    <span class="percentage-value">{{ infectionData.totalNumber }}</span>
                                    <span class="percentage-label">社区总人口</span>
                                </template>
                            </el-progress>

                            <!-- 存在基础病环形图 -->
                            <el-progress type="circle" :percentage="infectionData.isUdPercentage" status="warning">
                                <template #default="{ percentage }">
                                    <span class="percentage-value">{{ percentage }}%</span>
                                    <span class="percentage-label">存在基础病{{infectionData.isUdNumber}}人</span>
                                </template>
                            </el-progress>
                            
                            <!-- 阴性环形图 -->
                            <el-progress type="circle" :percentage="infectionData.femininePercentage" status="success">
                                <template #default="{ percentage }">
                                    <span class="percentage-value">{{ percentage }}%</span>
                                    <span class="percentage-label">阴性{{infectionData.feminineNumber}}人</span>
                                </template>
                            </el-progress>

                            <!-- 阳性环形图 -->
                            <el-progress type="circle" :percentage="infectionData.masculinePercentage" status="exception">
                                <template #default="{ percentage }">
                                    <span class="percentage-value">{{ percentage }}%</span>
                                    <span class="percentage-label">阳性{{infectionData.masculineNumber}}人</span>
                                </template>
                            </el-progress>

                            <!-- 社区风险等级 -->
                            <el-progress type="circle" :percentage="infectionData.masculinePercentage" :color="colors">
                                <template #default="{ percentage }">
                                    <span class="percentage-value">{{ percentage }}%</span>
                                    <span class="percentage-label">社区风险等级</span>
                                </template>
                            </el-progress>
                        </div>

                        <div class="stateStyle">
                            <el-row style="margin-bottom:15px">
                                <el-button type="primary" size="small">低风险</el-button>
                            </el-row>
                            <el-row style="margin-bottom:15px">
                                <el-button type="warning" size="small">中风险</el-button>
                            </el-row>
                            <el-row>
                                <el-button type="danger" size="small">高风险</el-button>
                            </el-row>
                        </div>
                    </div>

                </el-card>
            </el-col>

            <!-- 住户类型条形图 -->
            <el-col :span="8">
                <el-card shadow="hover">
                    <el-row>
                        <el-col :span="24">
                            <div class="grid-content ep-bg-purple-dark cardTitle">
                                当前社区住户类型情况
                            </div>
                        </el-col>
                    </el-row>
                    <div class="tiaoText">
                        租户{{infectionData.tenantNumber}}人，占比：
                    </div>
                    <el-progress :text-inside="true" :stroke-width="24" :percentage="infectionData.tenantPercentage" style="margin-bottom:6px" color="rgb(238, 102, 102)"/>
                    
                    <div class="tiaoText">
                        住户{{infectionData.holdNumber}}人，占比：
                    </div>
                    <el-progress :text-inside="true" :stroke-width="24" :percentage="infectionData.holdPercentage" status="warning" style="margin-bottom:6px"/>    
                </el-card>
            </el-col>
        </el-row>

        <el-row :gutter="10" style="margin-bottom:10px">
            <el-col :span="14">
                <el-card shadow="hover">
                    <!-- 未接种 -->
                    <el-row>
                        <el-col :span="4">
                            <div class="grid-content ep-bg-purple-dark">
                                未接种{{infectionData.noDoseNumber}}人
                            </div>
                        </el-col>
                        <el-col :span="20">
                            <div class="grid-content ep-bg-purple-dark">
                                <el-progress :text-inside="true" :stroke-width="22" :percentage="infectionData.noDosePercentage" color="#909399" style="margin-bottom:15px"/>
                            </div>
                        </el-col>
                    </el-row>
                    
                    <!-- 一针 -->
                    <el-row>
                        <el-col :span="4">
                            <div class="grid-content ep-bg-purple-dark">
                                一针{{infectionData.oneDoseNumber}}人
                            </div>
                        </el-col>
                        <el-col :span="20">
                            <div class="grid-content ep-bg-purple-dark">
                                <el-progress :text-inside="true" :stroke-width="22" :percentage="infectionData.oneDosePercentage" color="rgb(115, 192, 222)" style="margin-bottom:15px"/>
                            </div>
                        </el-col>
                    </el-row>
                    <!-- 两针 -->
                    <el-row>
                        <el-col :span="4">
                            <div class="grid-content ep-bg-purple-dark">
                                两针{{infectionData.twoDoseNumber}}人
                            </div>
                        </el-col>
                        <el-col :span="20">
                            <div class="grid-content ep-bg-purple-dark">
                                <el-progress :text-inside="true" :stroke-width="22" :percentage="infectionData.twoDosePercentage" color="rgb(84, 112, 198)" style="margin-bottom:15px"/>
                            </div>
                        </el-col>
                    </el-row>
                    <!-- 三针 -->
                    <el-row>
                        <el-col :span="4">
                            <div class="grid-content ep-bg-purple-dark">
                                三针{{infectionData.threeDoseNumber}}人
                            </div>
                        </el-col>
                        <el-col :span="20">
                            <div class="grid-content ep-bg-purple-dark">
                                <el-progress :text-inside="true" :stroke-width="22" :percentage="infectionData.threeDosePercentage" color="rgb(250, 200, 88)" style="margin-bottom:15px"/>
                            </div>
                        </el-col>
                    </el-row>
                    <!-- 四针 -->
                    <el-row>
                        <el-col :span="4">
                            <div class="grid-content ep-bg-purple-dark">
                                四针{{infectionData.foreDoseNumber}}人
                            </div>
                        </el-col>
                        <el-col :span="20">
                            <div class="grid-content ep-bg-purple-dark">
                                <el-progress :text-inside="true" :stroke-width="22" :percentage="infectionData.foreDosePercentage" color="#67C23A" />
                            </div>
                        </el-col>
                    </el-row>
                </el-card>
            </el-col>

            <el-col :span="10">
                <el-card shadow="hover"> 
                    <div>
                        <TimeClock></TimeClock>
                    </div>    
                </el-card>
            </el-col>
        </el-row>
        
        <el-row :gutter="10">
            <el-col :span="12">
                <el-card shadow="hover" style="height:226px">
                    <el-table :data="dateCDCP" height="200" style="width: 100%">
                        <el-table-column prop="cdcpName" label="疾控中心" width="300" />
                        <el-table-column prop="cdcpPhone" label="联系电话" width="280" />
                    </el-table>
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-card shadow="hover" style="height:226px">
                    <el-table :data="areaInfectionData.areaInfection" height="200" style="width: 100%">
                        <el-table-column prop="" label="区域感染状况（注：未显示的区域表明不存在感染情况）">
                            <el-table-column prop="areaName" label="区域名称" width="120" />
                            <el-table-column prop="areaTotalNumber" label="总人口数" width="85" />
                            <el-table-column prop="masculineNumber" label="感染人数" width="85" />
                            <el-table-column prop="infectionPercent" label="感染占比">
                                <template #default="scope">
                                    <el-progress :text-inside="true" :stroke-width="20" :percentage="scope.row.infectionPercent" status="exception" :color="colors"/>
                                </template>
                            </el-table-column>
                        </el-table-column>
                    </el-table>
                </el-card>
            </el-col>
        </el-row>
        
    </div>
</template>

<script>
// vue3相关组件
import { onMounted, reactive, ref, nextTick} from 'vue'

// 自定义局部刷新组件
import { useRouter } from "vue-router";
// 引入自定义时钟组件
import TimeClock from '../../../components/TimeModal/timeClock.vue'
// 引入人员信息（感染情况）统计--普通权限接口
import {infectionStatusForLNAPI} from '../../../http/api/personalAPI.js'
// 引入社区感染情况数据展示接口
import {selectAreaInfectionSituationAPI} from '../../../http/api/areaAPI.js'


export default {
    setup () {
        const router = useRouter(); //初始化路由（自定义局部刷新）
        // nextTick(() => { // 刷新页面（局部）
        //                     router.replace({
        //                         path: '/redirect' + '/AreaManagement',
        //                     })
        //                 });

        //时间组件
        // const myTime = reactive({
        //     yearDate: '',
        //     monthDate: '',
        //     dayDate: '',
        //     hourDate: '',
        //     minuteDate: '',
        //     secondDate: '',
        //     week: new Date().getDay(), //以数值获取周名（0-6）
        //     weeks: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
        // })
        // const myTimeData = () =>{
        //    myTime.yearDate = JSON.stringify(new Date().getFullYear());
        //    myTime.monthDate = JSON.stringify(new Date().getMonth()+1);
        //    myTime.dayDate = JSON.stringify(new Date().getDate());
        //    myTime.hourDate = JSON.stringify(new Date().getHours());
        //    myTime.minuteDate = JSON.stringify(new Date().getMinutes());
        //    myTime.secondDate = JSON.stringify(new Date().getSeconds());
        // }
        // setInterval(myTimeData, 1000);
        
        //社区风险数据
        const infectionData = reactive({ 
            totalNumber: 0, //总人数
            isUdNumber: 0, //存在基础病人数
            isUdPercentage: 0, //存在基础病人数占比
            feminineNumber: 0, //阴性人数
            femininePercentage: 0, //阴性人数占比
            masculineNumber: 0, //阳性人数
            masculinePercentage: 0, //阳性人数占比
            tenantNumber: 0, //租户人数
            tenantPercentage: 0, //租户人数占比
            holdNumber: 0, //住户人数
            holdPercentage: 0, //住户人数占比

            noDoseNumber: 0, //未接种人数
            noDosePercentage: 0,//未接种人数占比
            oneDoseNumber: 0, //接种一针人数
            oneDosePercentage: 0,//接种一针人数占比
            twoDoseNumber: 0, //接种两针人数
            twoDosePercentage: 0,//接种两针人数占比
            threeDoseNumber: 0, //接种三针人数
            threeDosePercentage: 0,//接种三针人数占比
            foreDoseNumber: 0, //接种四针人数
            foreDosePercentage: 0,//接种四针人数占比

            totalNum: 0,
        });
        

        //人员数据
        const sellectAllinfectiondData = infectionStatusForLNAPI().then(res =>{ //数据显示
           infectionData.totalNumber = res.data['totalNumber'];
           infectionData.isUdNumber = res.data['isUdNumber'];
           infectionData.feminineNumber = res.data['feminineNumber'];
           infectionData.masculineNumber = res.data['masculineNumber'];
           infectionData.tenantNumber = res.data['tenantNumber'];
           infectionData.holdNumber = res.data['holdNumber'];
           infectionData.noDoseNumber = res.data['noDoseNumber'];
           infectionData.oneDoseNumber = res.data['oneDoseNumber'];
           infectionData.twoDoseNumber = res.data['twoDoseNumber'];
           infectionData.threeDoseNumber = res.data['threeDoseNumber'];
           infectionData.foreDoseNumber = res.data['foreDoseNumber'];
           infectionData.totalNum = 100;

           //百分比（目标/总人口%）
            infectionData.isUdPercentage =Number((infectionData.isUdNumber/infectionData.totalNumber*100).toFixed(1));
            infectionData.femininePercentage =Number((infectionData.feminineNumber/infectionData.totalNumber*100).toFixed(1));
            infectionData.masculinePercentage =Number((infectionData.masculineNumber/infectionData.totalNumber*100).toFixed(1));
            infectionData.tenantPercentage =Number((infectionData.tenantNumber/infectionData.totalNumber*100).toFixed(1));
            infectionData.holdPercentage =Number((infectionData.holdNumber/infectionData.totalNumber*100).toFixed(1));
            console.log("数据："+JSON.stringify(infectionData.isUdPercentage));
            infectionData.noDosePercentage =Number((infectionData.noDoseNumber/infectionData.totalNumber*100).toFixed(1));
            infectionData.oneDosePercentage =Number((infectionData.oneDoseNumber/infectionData.totalNumber*100).toFixed(1));
            infectionData.twoDosePercentage =Number((infectionData.twoDoseNumber/infectionData.totalNumber*100).toFixed(1));
            infectionData.threeDosePercentage =Number((infectionData.threeDoseNumber/infectionData.totalNumber*100).toFixed(1));
            infectionData.foreDosePercentage =Number((infectionData.foreDoseNumber/infectionData.totalNumber*100).toFixed(1));
        });
        
        //社区感染情况数据展示
        const areaInfectionData = reactive({
            areaInfection: []
        });
        const selectAreaInfectionSituation = selectAreaInfectionSituationAPI().then(res =>{
            areaInfectionData.areaInfection = res.data;
            
        })

        //社区风险等级-颜色变动
        const colors = [
            { color: '#409eff', percentage: 21 },
            { color: '#e6a23c', percentage: 60 },
            { color: '#f56c6c', percentage: 60 },
        ];
        const communityRisk = ref(0); //定义环形进度初始值
        
        // onMounted(() => { //环形精度条变化范围复制
        //     setInterval(() => {
        //         if( communityRisk.value < infectionData.num){
        //             communityRisk.value = (communityRisk.value % 100) + 1
        //         };
                

        //     }, 30)
        // })

        const dateCDCP = [
            {
                cdcpName: '上海市疾病预防控制中心',
                cdcpPhone: '021-62758710'
            },
            {
                cdcpName: '上海市黄埔区疾病预防控制中心',
                cdcpPhone: '021-63013388'
            },
            {
                cdcpName: '上海市长宁区疾病预防控制中心',
                cdcpPhone: '021-520695950'
            },
            {
                cdcpName: '上海市徐汇区疾病预防控制中心',
                cdcpPhone: '021-54012700'
            },
            {
                cdcpName: '上海市静安区疾病预防控制中心',
                cdcpPhone: '021-56659090'
            },
            {
                cdcpName: '上海市普陀区疾病预防控制中心',
                cdcpPhone: '021-52828928'
            },
            {
                cdcpName: '上海市虹口区疾病预防控制中心',
                cdcpPhone: '021-52828928'
            },
            {
                cdcpName: '上海市杨浦区疾病预防控制中心',
                cdcpPhone: '021-65435824'
            },
            {
                cdcpName: '上海市闵行区疾病预防控制中心',
                cdcpPhone: '021-65435824'
            },
            {
                cdcpName: '上海市青浦区疾病预防控制中心',
                cdcpPhone: '021-33860590'
            },
        ]

        return {
            router,
            colors,
            communityRisk,
            infectionData,
            sellectAllinfectiondData,
            dateCDCP,
            areaInfectionData,
            selectAreaInfectionSituation,
            onMounted,
        }
    },

    components:{
        TimeClock,
    }
    
}
</script>

<style lang="scss" scoped>
.pageStyle{
    padding: 10px;
    height: calc(100vh - 115px);
    background-color: #f4f4f4;
}
.cardTitle{
    margin-bottom: 6px;
    color: #6b778c;
    font-size: 18px;
    font-weight: 600;
}
.tiaoText{
    font-size: 14px;
    font-weight: 500;
}
.displaStyle{
    display: flex;
}
.stateStyle{
    padding-top: 14px;
    padding-bottom: 14px;
    line-height: 130px;
}
</style>

<style lang="scss" scoped> //环形条
.demo-progress .el-progress--line {
  margin-bottom: 15px;
  width: 350px;
}
.demo-progress .el-progress--circle {
  margin-right: 15px;
}.percentage-value {
  display: block;
  margin-top: 10px;
  font-size: 28px;
}
.percentage-label {
  display: block;
  margin-top: 10px;
  font-size: 12px;
}
.demo-progress .el-progress--line {
  margin-bottom: 15px;
  width: 350px;
}
.demo-progress .el-progress--circle {
  margin-right: 15px;
}
</style>