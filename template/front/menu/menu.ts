import {api} from '@/utils/api-instance'

export const menuQueryOptions = async (keyword: string, id: string) => {
    return (
        await api.menuForAdminController.query({body: {query: {name: keyword, id}}})
    ).content
}