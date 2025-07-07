import {api} from '@/utils/api-instance'

export const userWeChatQueryOptions = async (keyword: string, id: string) => {
    return (
        await api.userWeChatForAdminController.query({body: {query: {name: keyword, id}}})
    ).content
}