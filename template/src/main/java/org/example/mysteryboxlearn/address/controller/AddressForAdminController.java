package org.example.mysteryboxlearn.address.entity.controller;

import org.example.mysteryboxlearn.address.entity.Address;

@RestController
@RequestMapping("admin/address")
@AllArgsConstructor
@DefaultFetcherOwner(AddressRepository.class)
@SaCheckPermission("/address")
@Transactional
public class AddressForAdminController {
    private final AddressRepository addressRepository;

    @GetMapping("{id}")
    public @FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") Address findById(@PathVariable String id) {
        return addressRepository.findById(id, AddressRepository.COMPLEX_FETCHER_FOR_ADMIN).orElseThrow(() -> new BusinessException("数据不存在"));
    }

    @PostMapping("query")
    public Page<@FetchBy(value = "COMPLEX_FETCHER_FOR_ADMIN") Address> query(@RequestBody QueryRequest<AddressSpec> queryRequest) {
        return addressRepository.findPage(queryRequest, AddressRepository.COMPLEX_FETCHER_FOR_ADMIN);
    }

    @PostMapping("save")
    public String save(@RequestBody @Validated AddressInput addressInput) {
        return addressRepository.save(addressInput.toEntity()).id();
    }

    @DeleteMapping
    public Boolean delete(@RequestBody List<String> ids) {
        addressRepository.deleteAllById(ids);
        return true;
    }
}