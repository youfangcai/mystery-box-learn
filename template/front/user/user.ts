import {api} from '@/utils/api-instance'

export const userQueryOptions = async (keyword: string, id: string) => {
    return (
        await api.userForAdminController.query({body: {query: {name: keyword, id}}})
    ).content
}