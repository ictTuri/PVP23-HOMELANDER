export interface PagedContent<T> {
    content: T[],
    pageable: Pageable,
    last: boolean,
    totalPages: number,
    totalElements: number,
    first: boolean,
    numberOfElements: number,
    size: number,
    number: number
    sort: Sort
}

export interface Pageable {
    sort: Sort,
    pageSize: number,
    pageNumber: number,
    offset: number,
    paged: boolean,
    unpaged: boolean
}

export interface Sort {
    unsorted: boolean,
    sorted: boolean
}

export interface LoginUser {
    credential: string,
    password: string
}

export interface RegisterUser {
    username: string,
    email: string,
    password: string
}

export interface User {
    username: string,
    firstName: string,
    lastName: string,
    phone: string,
    superuser: boolean,
    email: string,
    profilePicUuid: string,
    country: string,
    city: string,
    propertyOwned: number[],
    propertyRented: number[]
}

export interface Property {
    id: number,
    description: string,
    longDescriprion: string,
    size: number,
    unit: string,
    price: number,
    additionalAttributes: AdditionalAttributes,
    propertyAddress: PropertyAddress
}

export interface File {
    id: number, 
    fileName: string, 
    fileContent: string, 
    uuid: string
}

export interface AdditionalAttributes {
    furnished: boolean,
    typology: string,
    type: string, 
    appliance: string[],
    image: FileDto,
    images: FileDto[]
}

export interface PropertyAddress {
    address: string,
    city: string,
    country: string, 
    doorNumber: number,
    floorNumber: number,
    id: number,
    street: string,
    zone: string,
}


export interface FileDto {
    id: number,
    fileContent: string, 
    fileName: string, 
    uuid: string
}

export interface Error {
    message: string,
    description: string,
    suggestion: string
}

export interface Token {
    token: string,
    email: string
}

export interface ResponseDto {
    status: string,
    message: string
}

// User interfaces

export interface DetailedUser {
    username: string, 
    firstName: string, 
    lastName: string, 
    phone: string, 
    superuser: boolean, 
    email: string, 
    profilePicUUID: string, 
    country: string, 
    city: string, 
    canAddProperty: boolean, 
    canAddTenant: boolean, 
    propertyOwned: number[],
    propertyRented: number[]
}