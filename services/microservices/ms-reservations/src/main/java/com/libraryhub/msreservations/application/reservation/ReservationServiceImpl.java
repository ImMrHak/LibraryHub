package com.libraryhub.msreservations.application.reservation;

import com.libraryhub.msreservations.application.reservation.mapper.ReservationMapper;
import com.libraryhub.msreservations.application.reservation.record.request.CreateReservationDTO;
import com.libraryhub.msreservations.application.reservation.record.request.DeleteReservationDTO;
import com.libraryhub.msreservations.application.reservation.record.request.UpdateReservationDTO;
import com.libraryhub.msreservations.application.reservation.record.response.DataReservationDTO;
import com.libraryhub.msreservations.domain.service.ReservationDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationDomainService reservationDomainService;
    private final ReservationMapper reservationMapper;

    @Override
    public Object createReservation(CreateReservationDTO createReservationDTO) {
        return reservationDomainService.saveReservation(reservationMapper.mapCreateReservationDTOToReservation(createReservationDTO));
    }

    @Override
    public Object updateReservation(UpdateReservationDTO updateReservationDTO) {
        return null;
    }

    @Override
    public Object deleteReservation(DeleteReservationDTO deleteReservationDTO) {
        return null;
    }

    @Override
    public List<DataReservationDTO> getReservations() {
        return reservationDomainService.findAll().stream().map(reservationMapper::mapReservationToDataReservationDTO).collect(Collectors.toList());
    }

    @Override
    public DataReservationDTO getReservationById(Long id) {
        return reservationMapper.mapReservationToDataReservationDTO(reservationDomainService.findReservationById(id));
    }
}
