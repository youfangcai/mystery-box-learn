import {api} from '@/utils/api-instance'

export const paymentQueryOptions = async (keyword: string, id: string) => {
    return (
        await api.paymentForAdminController.query({body: {query: {name: keyword, id}}})
    ).content
}