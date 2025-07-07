import {api} from '@/utils/api-instance'

export const couponUserRelQueryOptions = async (keyword: string, id: string) => {
    return (
        await api.couponUserRelForAdminController.query({body: {query: {name: keyword, id}}})
    ).content
}