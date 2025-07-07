import {api} from '@/utils/api-instance'

export const dictQueryOptions = async (keyword: string, id: string) => {
    return (
        await api.dictForAdminController.query({body: {query: {name: keyword, id}}})
    ).content
}