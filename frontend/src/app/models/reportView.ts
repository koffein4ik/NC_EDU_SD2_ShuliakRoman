import { Post } from "./post";
import { User } from "./user";

export class ReportView {
    reason: string;
    post: Post;
    senderUser: User;
    reportedUser: User;
    date: string;
    status: string;
}