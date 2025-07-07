import {api} from '@/utils/api-instance'

export const couponCategoryRelQueryOptions = async (keyword: string, id: string) => {
    return (
        await api.couponCategoryRelForAdminController.query({body: {query: {name: keyword, id}}})
    ).content
}