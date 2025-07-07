import {api} from '@/utils/api-instance'

export const vipOrderQueryOptions = async (keyword: string, id: string) => {
    return (
        await api.vipOrderForAdminController.query({body: {query: {name: keyword, id}}})
    ).content
}