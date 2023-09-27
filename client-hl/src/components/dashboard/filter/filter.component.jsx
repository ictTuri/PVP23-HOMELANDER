import { Fragment } from 'react'
import React from 'react';
import Dropdown from './dropdown.component';
import { cities, types, rooms, min, max, category } from '../../../util/filter.constants';
import { useState } from 'react';

const defaultFormFields = {
    owner: "",
    forSale: null,
    forRent: null,
    rented: null,
    sold: null,
    sizeRange: {
        from: null,
        to: null
    },
    country: "",
    city: "",
    zone: "",
    typology: "",
    furnished: "",
    priceRange: {
        from: null,
        to: null
    },
    yearRange: {
        from: null,
        to: null
    }
};

export default function Filter() {
    const [filterData, setFilterData] = useState(defaultFormFields);

    const handleFilterForm = (filter, filterOption) => {
        setFilterData({
            ...filterData,
            [filterOption]: filter,
        });
        console.log(filterData);
    }

    const resetFormFields = () => {
        setFilterData(defaultFormFields);
    };

    const handleMinRange = (filter, filterOption) => {
        const range = filterData.$filter;
        console.log(range)
    }

    const handleMaxRange = (filter, filterOption) => {

    }

    return (

        <Fragment>
            <div className="flex h-full flex-row">
                <div className="sm:mx-auto">
                    <form className="space-y-2 flex-row grid">

                        <div>
                            <Dropdown onDataChange={handleFilterForm} data={cities} initial={"Qyteti"} />
                            <Dropdown onDataChange={handleFilterForm} data={types} initial={"Tipi i Prones"} />
                            <Dropdown onDataChange={handleFilterForm} data={rooms} initial={"Numri i Dhomave"} />
                            <Dropdown onDataChange={handleMinRange} data={min} initial={"Qmimi Minimal"} />
                            <Dropdown onDataChange={handleMaxRange} data={max} initial={"Qmimi Maximal"} />
                            <Dropdown onDataChange={handleFilterForm} data={category} initial={"Kategoria"} />
                        </div>

                        <div>
                            <button
                                type="submit"
                                className="w-56 flex justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                            >
                                Search
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </Fragment>
    )
}