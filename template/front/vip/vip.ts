import {api} from '@/utils/api-instance'

export const vipQueryOptions = async (keyword: string, id: string) => {
    return (
        await api.vipForAdminController.query({body: {query: {name: keyword, id}}})
    ).content
}