import {api} from '@/utils/api-instance'

export const couponBoxRelQueryOptions = async (keyword: string, id: string) => {
    return (
        await api.couponBoxRelForAdminController.query({body: {query: {name: keyword, id}}})
    ).content
}