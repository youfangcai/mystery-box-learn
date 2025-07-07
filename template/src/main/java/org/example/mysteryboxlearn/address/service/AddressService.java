package org.example.mysteryboxlearn.address.entity.service;

import org.example.mysteryboxlearn.address.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class AddressService {
    private final AddressRepository addressRepository;

}