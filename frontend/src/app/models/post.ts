import { User } from './user'; 
import { Hashtag } from './hashtag';

export class Post {
    postId: number;
    user: User;
    description: string;
    date: string;
    location: string;
    hashtags: Hashtag[];
    photoURIs: string[];
}