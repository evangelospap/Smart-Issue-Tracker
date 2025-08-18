import { useAuth } from "@clerk/nextjs";

// Backend base URL
const BACKEND_API = process.env.NEXT_PUBLIC_BACKEND_API || "http://localhost:8080";
const { getToken } = useAuth();

export async function fetchWithAuth(path: string, options: RequestInit = {}) {
  const token = await getToken({ template: "Smart-Issue-Tracker"});

  const headers = {
    ...options.headers,
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  const res = await fetch(`${BACKEND_API}${path}`, { ...options, headers });

  if (!res.ok) {
    const errorText = await res.text();
    throw new Error(`Request failed: ${res.status} - ${errorText}`);
  }

  return res.json();
}
