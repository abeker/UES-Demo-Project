package com.practice.demo.service;

import com.practice.demo.dto.ReaderDto;
import com.practice.demo.model.Reader;
import com.practice.demo.repository.IReaderRepository;
import org.springframework.stereotype.Service;

@Service
public class ReaderService implements IReaderService {

    private final IReaderRepository _readerRepository;

    public ReaderService(IReaderRepository readerRepository) {
        _readerRepository = readerRepository;
    }

    @Override
    public void index(ReaderDto readerDto) {
        _readerRepository.save(
            new Reader(
                    "123",
                    readerDto.getFirstName(),
                    readerDto.getLastName(),
                    readerDto.getAddress()
            )
        );
    }
}
