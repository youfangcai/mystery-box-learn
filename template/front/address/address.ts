import {api} from '@/utils/api-instance'

export const addressQueryOptions = async (keyword: string, id: string) => {
    return (
        await api.addressForAdminController.query({body: {query: {name: keyword, id}}})
    ).content
}