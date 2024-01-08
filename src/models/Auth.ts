export interface Auth {
  nim: number | null;
  password: string;
}

export interface Register {
  nim: number | null;
  name: string;
  password: string;
}
