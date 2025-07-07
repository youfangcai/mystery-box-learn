import {api} from '@/utils/api-instance'

export const refundRecordQueryOptions = async (keyword: string, id: string) => {
    return (
        await api.refundRecordForAdminController.query({body: {query: {name: keyword, id}}})
    ).content
}