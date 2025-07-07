import {api} from '@/utils/api-instance'

export const couponReceiveActivityQueryOptions = async (keyword: string, id: string) => {
    return (
        await api.couponReceiveActivityForAdminController.query({body: {query: {name: keyword, id}}})
    ).content
}