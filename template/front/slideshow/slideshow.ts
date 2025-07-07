import {api} from '@/utils/api-instance'

export const slideshowQueryOptions = async (keyword: string, id: string) => {
    return (
        await api.slideshowForAdminController.query({body: {query: {name: keyword, id}}})
    ).content
}