import {api} from '@/utils/api-instance'

export const mysteryBoxProductRelQueryOptions = async (keyword: string, id: string) => {
    return (
        await api.mysteryBoxProductRelForAdminController.query({body: {query: {name: keyword, id}}})
    ).content
}