/*
 * Copyright 2020-2030 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.group.foctg.holidayMaker.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group.foctg.holidayMaker.model.Accommodation;
import com.group.foctg.holidayMaker.model.DateChecker;
import com.group.foctg.holidayMaker.model.Filter;
import com.group.foctg.holidayMaker.model.Room;
import com.group.foctg.holidayMaker.repositories.AccommodationRepository;
import java.util.Optional;

/**
 * Service class for the {@link com.group.foctg.holidayMaker.model.Accommodation} 
 * column and entity. Autowires the repository.
 *
 * @author Olle Johansson
 *
 * @see com.group.foctg.holidayMaker.repositories.AccoommodationRepository
 */
@Service
@Transactional
public class AccommodationService {

	@Autowired
	private AccommodationRepository accommodationRepository;

	/**
	 * Saves the {@link com.group.foctg.holidayMaker.model.Accommodation} object
	 * from parameter in the database.
	 *
	 * @param accommodation {@link com.group.foctg.holidayMaker.model.Accommodation}
	 * object that shall be saved.
	 * @return A boolean value representing whether the saving was successful or not.
	 */
	public boolean saveAccommodation(Accommodation accommodation) {
		return accommodationRepository.saveAndFlush(accommodation).equals(accommodation);
	}

	/**
	 * Removes the {@link com.group.foctg.holidayMaker.model.Accommodation} object
	 * with the same <code>id</code> as the parameter from the database.
	 *
	 * @param id Long value used for finding and removing
	 * {@link com.group.foctg.holidayMaker.model.Accommodation} with that
	 * <code>id</code>
	 * @return A boolean value representing whether the removing 
	 * was successful or not.
	 */
    public boolean removeAccommodationById(Long id) {
        if (accommodationRepository.existsById(id)) {
            accommodationRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


	/**
	 * If there is a Accommodation object already that has the same id as the
	 * {@link com.group.foctg.holidayMaker.model.Accommodation} passed as parameter
	 * then it'll update the existing object. Otherwise it will save the object.
	 *
	 * @param accommodation {@link com.group.foctg.holidayMaker.model.Accommodation}
	 * object passed for updating or saving.
	 * @return A boolean value representing whether the updating or saving was
	 * successful or not.
	 */
	public boolean updateAccommodation(Accommodation accommodation) {
		return accommodationRepository.saveAndFlush(accommodation).equals(accommodation);
	}

	/**
	 * Goes through the database, checks and returns all
	 * {@link com.group.foctg.holidayMaker.model.Accommodation} objects in the
	 * List&lt;{@link com.group.foctg.holidayMaker.model.Accommodation}&gt;
	 * 
	 * @return List&lt;{@link com.group.foctg.holidayMaker.model.Accommodation}&gt;
	 */
	public List<Accommodation> findAll() {
		return accommodationRepository.findAll();
	}

    /**
     * Goes through the database, checks and returns all
     * {@link com.group.foctg.holidayMaker.model.Accommodation} objects in the
     * List&lt;{@link com.group.foctg.holidayMaker.model.Accommodation}&gt; if a
     * customer with given <code>id</code> exists.
     *
     * @param id Long value to use for finding the
     * {@link com.group.foctg.holidayMaker.model.Accommodation}
     * @return {@link com.group.foctg.holidayMaker.model.Accommodation} with the
     * given <code>id</code>, if it exists.
     */
	public Accommodation getOne(Long id) {
		return accommodationRepository.getOne(id);
	}

    /**
     * Goes through the database, checks and returns one
     * {@link com.group.foctg.holidayMaker.model.Accommodation} object in the
     * List&lt;{@link com.group.foctg.holidayMaker.model.Accommodation}&gt; that
     * matches the <code>ID</code>
     *
     * @param id
     * @return
     */
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

	/**
     * List&lt;{@link com.group.foctg.holidayMaker.model.Accommodation}&gt; that
     * matches the <code>ID</code> of a
     * {@link com.group.foctg.holidayMaker.model.Customer}
     *
     * @param id
     * @return
     */
    public List<Accommodation> findAccommodationsByCustomerId(Long id) {
        return accommodationRepository.findAccommodationsByCustomerId(id);
    }
    	
   /**
     *
     * @param filter
     * @return
     * @throws ParseException
     */
    public List<Accommodation> getFilteredAccommodations(Filter filter) throws ParseException {
        Set<Accommodation> availableByDate = new HashSet<>();

        /**
         * These three nested for-each loops will check if the input filter
         * dates overlap the rooms taken dates. If the filter-dates overlap with
         * the rooms taken-dates it will return true. We check if the return is
         * false and add that accommodation with available dates to our
         * availableByDate List
         */
        for (Accommodation a : findAll()) {
            for (Room r : a.getRooms()) {
                for (String[] dates : r.getDatesTaken()) {
                    Date df1 = new SimpleDateFormat("dd/MM/yyyy").parse(filter.getDateFrom());
                    Date dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(filter.getDateTo());
                    Date df2 = new SimpleDateFormat("dd/MM/yyyy").parse(dates[0]);
                    Date dt2 = new SimpleDateFormat("dd/MM/yyyy").parse(dates[1]);

                    if (!DateChecker.isOverlapping(df1, dt1, df2, dt2)) {
                        availableByDate.add(a);
                    } else {
                        break;
                    }
                }
            }
        }

        /**
         * These anonymous functions returns true/false depending on the
         * statement. If the statement is true, the filter() function will pass
         * the object of
         * {@link com.group.foctg.holidayMaker.model.Accommodation} and be
         * appended to the List<Accommodation>
         */
        List<Accommodation> filtered = availableByDate.stream()
                .filter(a -> a.getDistanceToBeach() > filter.getMinDistBeach() && a.getDistanceToBeach() < filter.getMaxDistBeach())
                .filter(a -> a.getDistanceToCenter() > filter.getMinDistCenter() && a.getDistanceToCenter() < filter.getMaxDistCenter())
                .filter(a -> a.getLocation().getName().equals(filter.getLocation()))
                .filter(a -> a.getPool() == true || filter.hasPool() == false)
                .filter(a -> a.getChildEvents() == true || filter.hasChildrenClub() == false)
                .filter(a -> a.getRestaurant() == true || filter.hasRestaurant() == false)
                .filter(a -> a.getNightEntertainment() == true || filter.hasNightEntertainment() == false)
                .filter(a -> a.getRooms().size() >= filter.getRooms())
                .collect(Collectors.toList());

        return filtered;
    }

	/**
	 * Goes through the database, checks and returns all
	 * {@link com.group.foctg.holidayMaker.model.Accommodation} objects that has
	 * <code>distanceToBeach</code> within given argument.
	 *
	 * @param distance Short value to use for finding the
	 * {@link com.group.foctg.holidayMaker.model.Accommodation}
	 * @return List&lt;{@link com.group.foctg.holidayMaker.model.Accommodation}&gt;
	 */
	public List<Accommodation> findAccommodationsWithinDistanceToBeach(Short distance) {
		return accommodationRepository.findAccommodationsByDistanceToBeach(distance);
	}

	/**
	 * Goes through the database, checks and returns all
	 * {@link com.group.foctg.holidayMaker.model.Accommodation} objects that has
	 * <code>distanceToCenter</code> within given argument.
	 *
	 * @param distance Short value to use for finding the
	 * {@link com.group.foctg.holidayMaker.model.Accommodation}
	 * @return List&lt;{@link com.group.foctg.holidayMaker.model.Accommodation}&gt;
	 */
	public List<Accommodation> findAccommodationsWithinDistanceToCenter(Short distance) {
		return accommodationRepository.findAccommodationsByDistanceToCenter(distance);
	}

	/**
	 * Goes through the database, checks and returns all
	 * {@link com.group.foctg.holidayMaker.model.Accommodation} objects that has the
	 * <code>rating</code> within given argument.
	 *
	 * @param rating Float value to use for finding the
	 * {@link com.group.foctg.holidayMaker.model.Accommodation}
	 * @return List&lt;{@link com.group.foctg.holidayMaker.model.Accommodation}&gt;
	 */
	public List<Accommodation> findAccommodationsByRating(Float rating) {
		return accommodationRepository.findAccommodationsByRating(rating);
	}
}
