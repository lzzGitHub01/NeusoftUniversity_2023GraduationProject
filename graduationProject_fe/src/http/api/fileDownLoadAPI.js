import $axios from '../index.js'
//==========================================文件导出相关API接口==========================================
/**
 * 区域人员管理Excel文件导出
 * @returns 
 */
export const excelFileDownloadAPI =()=>{
    return $axios({
        url:'fileExport/excelFileDownload',
        method:'get',
    })
}