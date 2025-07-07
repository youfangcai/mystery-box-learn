import {api} from '@/utils/api-instance'

export const carriageTemplateQueryOptions = async (keyword: string, id: string) => {
    return (
        await api.carriageTemplateForAdminController.query({body: {query: {name: keyword, id}}})
    ).content
}