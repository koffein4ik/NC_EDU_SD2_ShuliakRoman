export class StorageUserModel {

    constructor(id: number, nickname: string, role: string) {
        this.id = id;
        this.username = nickname;
        this.role = role;
    }

    id: number;
    username: string;
    role: string;
}