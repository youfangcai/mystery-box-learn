import {api} from '@/utils/api-instance'

export const feedbackQueryOptions = async (keyword: string, id: string) => {
    return (
        await api.feedbackForAdminController.query({body: {query: {name: keyword, id}}})
    ).content
}