<template>
    <div class="timeClock">
        <el-row class="topStyle" type="flex" justify="center" align="middle">
            {{myTime.hourDate}}:{{myTime.minuteDate}}:{{myTime.secondDate}}
        </el-row>
        <el-row class="bottomStyle" type="flex" justify="center" align="middle">
            {{myTime.yearDate+"年"}}{{myTime.monthDate+"月"}}{{myTime.dayDate+"日"}} | {{myTime.weeks[myTime.week]}}
        </el-row>
        <el-row>
            
        </el-row>
    </div>
</template>

<script lang="ts">
// vue3相关组件
import { reactive, ref, nextTick} from 'vue'

export default {
    setup () {
        const myTime = reactive({
            yearDate: '',
            monthDate: '',
            dayDate: '',
            hourDate: '',
            minuteDate: '',
            secondDate: '',
            week: new Date().getDay(), //以数值获取周名（0-6）
            weeks: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
        })
        const myTimeData = () =>{
            var year = JSON.stringify(new Date().getFullYear());
            var month = JSON.stringify(new Date().getMonth()+1);
            var day = JSON.stringify(new Date().getDate());
            var hour = JSON.stringify(new Date().getHours());
            var minute = JSON.stringify(new Date().getMinutes());
            var second = JSON.stringify(new Date().getSeconds());

            if(month.length == 1){
                month = '0' + JSON.stringify(new Date().getMonth()+1);
            }
            if(day.length == 1){
                day = '0' + JSON.stringify(new Date().getDate());
            }
            if(hour.length == 1){
                hour = '0' + JSON.stringify(new Date().getHours());
            }
            if(minute.length == 1){
                minute = '0' + JSON.stringify(new Date().getMinutes());
            }
            if(second.length == 1){
                second = '0' + JSON.stringify(new Date().getSeconds());
            }
            
            myTime.yearDate = year;
            myTime.monthDate = month;
            myTime.dayDate = day;
            myTime.hourDate = hour;
            myTime.minuteDate = minute;
            myTime.secondDate = second;
        }
        setInterval(myTimeData, 1000);

        return {
            myTime,
            myTimeData,
        }
    }
}
</script>

<style lang="scss" scoped>
.timeClock{
    // background-color: black;
    color: #6b778c;
    padding: 10px 40px 22px 40px;
}
.topStyle{
    font-size: 90px;
    font-weight: 500;
    width: 380px;
}
.bottomStyle{
    font-size: 26px;
    font-weight: 400;
    margin-left: 8px;
}
</style>