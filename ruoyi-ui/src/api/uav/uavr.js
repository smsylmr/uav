import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

// 查询无人机类型列表
export function listUavUsers(data) {
  return request({
    url: '/system/uav/users',
    method: 'get',
    params: {}
  })
}

// 无人机维保新增
export function uavRepairInsert(data) {
  return request({
    url: '/system/uav/repair/',
    method: 'post',
    data: data
  })
}
// 无人机类型修改
export function uavUpdate(data) {
  return request({
    url: '/system/uav',
    method: 'put',
    data: data
  })
}

// 查询无人机维保列表
export function uavRepairList(query) {
  return request({
    url: '/system/uav/repair/list',
    method: 'get',
    params: query
  })
}
// 删除无人机类型
export function uavDelete(userId) {
  return request({
    url: '/system/uav/' + userId,
    method: 'delete'
  })
}
// 查询无人机类型详细信息
export function uavInfo(uavTypeId) {
  return request({
    url: '/system/uav/' + parseStrEmpty(uavTypeId),
    method: 'get'
  })
}
